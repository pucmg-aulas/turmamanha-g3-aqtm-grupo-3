public class Vaga {
    private String id;
    private String tipo;
    private boolean status;

    public Vaga(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.status = false;
    }

    public void ocuparVaga() {
        this.status = true;
    }

    public void desocuparVaga() {
        this.status = false;
    }

}
