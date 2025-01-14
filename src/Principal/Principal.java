package Principal;

import Classes.Casella;
import Classes.Inventari;
import Classes.ItemInventari;
import Classes.Jardi;
import Classes.Llavor;
import Classes.Partida;
import Classes.Planta;
import TipusDePlantes.PlantaGirasol;
import TipusDePlantes.PlantaTrebolDeLaSort;
import TipusDePlantes.TipusDePlanta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Principal {

    static Scanner sc;
    static Partida p;

    public static void desaLaPartida() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p3Martin.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (ItemInventari item : p.getInventari().getLlavors()) {
            em.merge(item.getLlavor());
            em.merge(item.getLlavor().getTipus());
            em.merge(item);
        }
        em.merge(p.getInventari());

        for (int x = 0; x < p.jardi.mapa.length; x++) {
            for (int y = 0; y < p.jardi.mapa[x].length; y++) {
                Casella casella = p.getJardi().getMapa()[x][y];
                if (casella != null) {
                    em.merge(casella.getPlanta().getTipus());
                    em.merge(casella.getPlanta());
                    em.merge(casella);

                }
            }
        }

        if (em.find(Jardi.class, p.jardi.id) == null) {
            em.merge(p.jardi);
        }

        if (em.find(Partida.class, p.id) == null) {
            em.merge(p);
        }

        em.getTransaction()
                .commit();
        em.close();

        emf.close();

    }

    public static void carregaPartida() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p3Martin.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        TypedQuery<Partida> consultaPartida = em.createQuery("SELECT p FROM Partida p", Partida.class
        );
        List<Partida> resultatConsultaPartida = consultaPartida.getResultList();

        if (resultatConsultaPartida.isEmpty()) {
            System.out.println("No n'hi ha cap partida per carregar");
        }

        Partida partidaCarregada = resultatConsultaPartida.get(0);
        p = partidaCarregada;

        em.getTransaction().commit();
        System.out.println("Partida carregada correctament");

        em.close();
        emf.close();

        joc(false);
    }

    public static void venPlantes() {
        System.out.println("Escriu la fila de la planta que vols vendre: ");
        int filaEscollida = sc.nextInt();
        sc.nextLine();
        System.out.println("Escriu la columna de la planta que vols vendre: ");
        int columnaEscollida = sc.nextInt();
        sc.nextLine();

        if (filaEscollida < 0 || filaEscollida >= 10) {
            System.out.println("Aquesta coordenada no és vàlida!");
            return;
        }
        if (columnaEscollida < 0 || columnaEscollida >= 10) {
            System.out.println("Aquesta coordenada no és vàlida!");
            return;
        }
        if (p.jardi.mapa[filaEscollida][columnaEscollida] != null) {
            // Recuperem el valor de venda de la planta
            int valorDeVenda = p.jardi.mapa[filaEscollida][columnaEscollida].planta.tipus.getValorDeVenda();
            p.inventari.diners += valorDeVenda;

            // Eliminem la planta
            p.jardi.mapa[filaEscollida][columnaEscollida] = null;

            System.out.println("S'ha venut la planta correctament.");

        } else {
            System.out.println("Aquesta casella no té cap planta!");
        }
    }

    public static void plantaAlJardi() {
        System.out.println("0-Cancel·la");
        int quantitat = 1;

        for (ItemInventari ii : p.inventari.llavors) {
            System.out.println(quantitat + "-" + ii.llavor.nom + ": " + ii.quantitat);
            quantitat += 1;
        }

        int opcio = sc.nextInt();
        sc.nextLine();
        if (opcio == 0) {
            System.out.println("Cancel·lant...");
            return;
        } else {
            if (opcio <= p.inventari.llavors.size()) {
                opcio -= 1;
                ItemInventari itemInventari = p.inventari.llavors.get(opcio);
                if (itemInventari.quantitat <= 0) {
                    System.out.println("Ja no et queden llavors d'aquest tipus.");
                    return;
                }
                // Plantacio de la llavor
                boolean valid = false;
                while (!valid) {
                    System.out.println("A quina fila vols plantar la " + itemInventari.llavor.nom + "?");
                    int filaEscollida = sc.nextInt();
                    sc.nextLine();
                    if (filaEscollida < 0 || filaEscollida >= 10) {
                        System.out.println("Aquesta coordenada no és vàlida!");
                        continue;
                    }

                    System.out.println("A quina columna vols plantar la " + itemInventari.llavor.nom + "?");
                    int columnaEscollida = sc.nextInt();
                    sc.nextLine();

                    if (columnaEscollida < 0 || columnaEscollida >= 10) {
                        System.out.println("Aquesta coordenada no és vàlida!");
                        continue;
                    }

                    if (p.jardi.mapa[filaEscollida][columnaEscollida] == null) {
                        // Escollim el tipus de planta
                        Planta planta = null;
                        if (itemInventari.llavor.tipus.nomTipus.equals("Girasol")) {
                            planta = new Planta(new PlantaGirasol(filaEscollida, columnaEscollida), 0);
                        } else if (itemInventari.llavor.tipus.nomTipus.equals("Trebol de la sort")) {
                            planta = new Planta(new PlantaTrebolDeLaSort(filaEscollida, columnaEscollida), 0);
                        }

                        p.jardi.mapa[filaEscollida][columnaEscollida] = new Casella(planta, true);
                        valid = true;
                        p.inventari.llavors.get(p.inventari.llavors.indexOf(itemInventari)).quantitat -= 1;
                        if (p.inventari.llavors.get(p.inventari.llavors.indexOf(itemInventari)).quantitat == 0) {
                            p.inventari.llavors.remove(itemInventari);
                        }
                    } else {
                        System.out.println("Aquesta casella ja està ocupada!");
                    }

                }

            }
        }
    }

    public static void revisaInventari() {
        for (ItemInventari ii : p.inventari.llavors) {
            System.out.println(ii.llavor.nom + ": " + ii.quantitat);
            System.out.println(ii.llavor.tipus.explicacioEfecte);
        }
        System.out.println("Prem enter per a continuar...");
        sc.nextLine();
    }

    public static void finalitzaElTorn(ArrayList<TipusDePlanta> tipusDePlantes, ArrayList<Llavor> llavors) {

        // Activem l'efecte de totes les plantes
        for (int i = 0; i < p.jardi.mapa.length; i++) {
            for (int j = 0; j < p.jardi.mapa[i].length; j++) {
                Casella casella = p.jardi.mapa[i][j];
                if (casella != null) {
                    casella.planta.tipus.efecte();
                }
            }
        }

        // Fem que totes les plantes creixin 1 dia
        for (int i = 0; i < p.jardi.mapa.length; i++) {
            for (int j = 0; j < p.jardi.mapa[i].length; j++) {
                Casella casella = p.jardi.mapa[i][j];
                if (casella != null) {
                    casella.planta.edat += 1;
                }
            }
        }
        // Passem el dia
        p.dia += 1;

        // Mostrem les noves llavors que pot obtenir el jugador
        Random rand = new Random();
        int i1 = rand.nextInt(tipusDePlantes.size());
        int i2 = rand.nextInt(tipusDePlantes.size());
        int i3 = rand.nextInt(tipusDePlantes.size());

        Llavor l1 = llavors.get(i1);
        Llavor l2 = llavors.get(i2);
        Llavor l3 = llavors.get(i3);

        boolean valid = false;
        int opcio = -1;
        while (!valid) {
            System.out.println("Escull una llavor abans d'acabar el dia:");
            System.out.println("1-" + l1.nom);
            System.out.println(l1.tipus.explicacioEfecte);
            System.out.println("");
            System.out.println("2-" + l2.nom);
            System.out.println(l2.tipus.explicacioEfecte);
            System.out.println("");
            System.out.println("3-" + l3.nom);
            System.out.println(l3.tipus.explicacioEfecte);
            System.out.println("");

            opcio = sc.nextInt();
            sc.nextLine();
            valid = true;
            Llavor chosenLlavor = null;
            switch (opcio) {
                case 1:
                    chosenLlavor = l1;
                    break;
                case 2:
                    chosenLlavor = l2;
                    break;
                case 3:
                    chosenLlavor = l3;
                    break;
                default:
                    System.out.println("Aquesta opció no és vàlida.");
                    valid = false;
            }

            boolean found = false;
            for (ItemInventari ii : p.inventari.llavors) {
                if (ii.llavor.equals(chosenLlavor)) {
                    ii.quantitat += 1;
                    found = true;
                }
            }
            if (!found) {
                p.inventari.llavors.add(new ItemInventari(chosenLlavor, 1, p.inventari));
            }

        }

    }

    public static void joc(boolean novaPartida) {
        ArrayList<TipusDePlanta> tipusDePlantes = new ArrayList<>();
        tipusDePlantes.add(new PlantaGirasol(p));
        tipusDePlantes.add(new PlantaTrebolDeLaSort(p));

        ArrayList<Llavor> llavors = new ArrayList<>();
        llavors.add(new Llavor(tipusDePlantes.get(0).nomLlavor, tipusDePlantes.get(0)));
        llavors.add(new Llavor(tipusDePlantes.get(1).nomLlavor, tipusDePlantes.get(1)));

        // Afegim les llavors inicials:
        if (novaPartida) {
            p.inventari.llavors.add(new ItemInventari(llavors.get(0), 3, p.inventari));
        }
        // Mostrem els efectes de les llavors inicials:
        System.out.println("Llavors inicials:");
        revisaInventari();

        int opcio = -1;

        while (opcio != 0) {
            try {
                // Netejem la pantalla
                new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();

            } catch (IOException ex) {
                Logger.getLogger(Principal.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            // Mostrem els diners actuals
            System.out.println("Diners: " + p.inventari.diners);
            // Mostrem el dia actual
            System.out.println("Dia: " + p.dia);

            if (p.dia != 0 && p.dia % 5 == 0) {
                // Toca pagar.
                int quantitatDeguda = p.dia * 50;
                System.out.println("Ha arribat el dia de pagar. La quantitat és: " + quantitatDeguda);
                if (p.inventari.diners < quantitatDeguda) {
                    // No pot pagar.
                    System.out.println("No pots pagar, has perdut la partida.");
                    System.out.println("Prem enter per a continuar...");
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Has pagat, pots seguir jugant.");
                    p.inventari.diners -= quantitatDeguda;
                }
                System.out.println("Prem enter per a continuar...");
                sc.nextLine();

            }

            // Mostrem el jardí
            for (int i = 0; i < p.jardi.mapa.length; i++) {
                for (int j = 0; j < p.jardi.mapa[i].length; j++) {
                    Casella casella = p.jardi.mapa[i][j];
                    if (casella == null) {
                        System.out.print(" - ");
                    } else {
                        System.out.print(" " + casella + " ");
                    }

                }
                System.out.println("");
            }

            // Mostrem les opcions
            System.out.println("0-Desa i surt de la partida");
            System.out.println("1-Ven plantes");
            System.out.println("2-Planta al jardí");
            System.out.println("3-Revisa l'inventari");
            System.out.println("4-Finalitza el torn");

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 0:
                    // Acaba la partida
                    desaLaPartida();
                    break;
                case 1:
                    // Venem plantes
                    venPlantes();
                    break;
                case 2:
                    // Plantem al jardí
                    plantaAlJardi();
                    break;
                case 3:
                    // Revisa l'inventari
                    revisaInventari();
                    break;
                case 4:
                    // Passem de dia
                    finalitzaElTorn(tipusDePlantes, llavors);
                    break;
                default:
                    throw new AssertionError();
            }

        }

    }

    public static void novaPartida() {
        p = new Partida();
        p.jardi = new Jardi();
        p.inventari = new Inventari(new ArrayList<ItemInventari>());
        p.dia = 0;

        // Iniciem el joc
        joc(true);
    }

    public static void veureInstruccions() {
        System.out.println("INSTRUCCIONS JOC DEL JARDÍ");
        System.out.println("Benvingut al joc del jardí. En aquest joc, tindras un jardí on hi plantaràs plantes.");
        System.out.println("A cada torn, pots revisar el teu inventari de llavors, plantar una planta o vendre les que ja has plantat.");
        System.out.println("Cada planta té un efecte diferent!");
        System.out.println("Per cada dia que passa, les plantes del jardí generen diners.");
        System.out.println("Cada 5 dies, has de pagar una fiança.");
        System.out.println("Si no pots pagar, perds.");

        System.out.println("\nPrem enter per a continuar...");
        sc.nextLine();
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        int opcio = -1;

        while (opcio != 0) {
            try {
                // Netejem la pantalla
                new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();

            } catch (IOException ex) {
                Logger.getLogger(Principal.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Benvingut al joc del jardí.");
            System.out.println("0-Surt del joc");
            System.out.println("1-Nova partida");
            System.out.println("2-Carrega la partida");
            System.out.println("3-Veure les instruccions");

            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 0:
                    // Surt del joc
                    System.out.println("Adéu!");
                    break;
                case 1:
                    // Nova partida
                    novaPartida();
                    break;
                case 2:
                    // Carrega la partida
                    carregaPartida();
                    break;
                case 3:
                    // Veure les instruccions
                    veureInstruccions();
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }
}
