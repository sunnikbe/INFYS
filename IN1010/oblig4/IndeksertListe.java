class IndeksertListe <E> extends Lenkeliste <E> {
    
    public void leggTil(int pos, E x){
        // Error hvis indeksen ikke finnes i listen
        if (pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node ny = new Node(x);
        // Hvis elementet skal legges inn på indeks 0
        if (pos == 0) {
            ny.neste = start;
            start = ny;
        } else {
            Node peker = start;
            /* Teller frem til der peker er rett før pos 
            slik at peker.neste etter loopen er der vi vil 
            legge til x */
            for (int i = 0; i < pos - 1; i++){
                peker = peker.neste;
            }
            /* Holder på den gamle verdien som er i posisjonen
            x skal legges til i, setter inn ny i posisjonen. 
            Setter neste for den nye verdien til å peke på 
            den gamle verdien. */
            Node gammel = peker.neste;
            peker.neste = ny;
            ny.neste = gammel;
        }
    }

    public void sett(int pos, E x){
        // Error hvis indeksen ikke finnes i listen
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node ny = new Node(x);
        /* leggTil() kalles ved tom liste */
        if (stoerrelse() == 0){
            super.leggTil(x);
        } else if (pos == 0) {
            start = ny;
        } else {
            Node peker = start;
            // Teller frem til der peker er rett før pos
            for (int i = 0; i < pos - 1; i++){
                peker = peker.neste;
            }
            /* Setter inn ny etter peker, og setter ny til å peke 
            på verdien etter posisjonen hvor ny er satt inn*/
            Node etter = peker.neste.neste;
            peker.neste = ny;
            ny.neste = etter;
        }
    }


    public E hent(int pos){
        // Error hvis indeksen ikke finnes i listen
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        E ut = null;
        Node peker = start;
        for (int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        ut = peker.element;
        return ut;
    }

    public E fjern(int pos){
        // Error hvis indeksen ikke finnes i listen
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        E ut = null;
        Node peker = start;
        if (pos == 0){
            ut = start.element;
            start = start.neste;
        } else {
        for (int i = 0; i < pos - 1; i++){
            peker = peker.neste;
        }
        ut = peker.neste.element;
        peker.neste = peker.neste.neste;
        }
        return ut;
    }
}