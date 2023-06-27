class Stabel<E> extends Lenkeliste<E> {

    // Legger til element i starten av listen
    @Override
    public void leggTil(E x){
        Node ny = new Node(x);
        ny.neste = start;
        start = ny;
    }
}