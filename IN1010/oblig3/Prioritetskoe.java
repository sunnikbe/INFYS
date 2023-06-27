class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        // Hvis listen er tom, eller elementet er større enn siste
        if (stoerrelse() == 0) {
            super.leggTil(x);
        } else if (stoerrelse() == 1) {
            if (x.compareTo(start.element) > 0){
                super.leggTil(x);
            } else {
                Node temp = start;
                start = ny;
                ny.neste = temp;
                slutt = temp;
            }
        } else if (x.compareTo(slutt.element) < 0 && x.compareTo(start.element) > 0) {
            Node peker = start;
            while (x.compareTo(peker.element) > 0) {
                peker = peker.neste;
            }
            Node gammel = peker.neste;
            peker.neste = ny;
            ny.neste = gammel;
        // Minste verdier skal først i listen
        // Hvis x er mindre eller lik første element i listen
        } else if (x.compareTo(slutt.element) >= 0) {
            super.leggTil(x);
        } else if (x.compareTo(start.element) <= 0) {
            ny.neste = start;
            start = ny;
        }
    }

    /* Hent og fjern metodene fra Lenkeliste henter/fjerner 
    det første (her minste) fra før av */
}