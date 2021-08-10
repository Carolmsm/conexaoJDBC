import java.sql.Time;

public class Curso {

    private int id;
    private String nome;
    private Time duracao_horas;

    public Curso() { }

    public Curso( int id, String nome, Time duracao_horas) {
        this.id = id;
        this.nome = nome;
        this.duracao_horas = duracao_horas;
    }
    public Curso(String nome, Time duracao_horas) {
        this.nome = nome;
        this.duracao_horas = duracao_horas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getDuracao_horas() {
        return duracao_horas;
    }

    public void setDuracao_horas(Time duracao_horas) {
        this.duracao_horas = duracao_horas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Curso{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome).append('\'');
        sb.append(", duracao_horas").append(duracao_horas);
        sb.append('}');
        return sb.toString();


    }
}
