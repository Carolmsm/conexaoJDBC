
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    // 1 - Consulta

    public List<Curso> list() {

        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM curso";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Time duracao_horas = rs.getTime("duracao_horas");

                cursos.add(new Curso(
                        id,
                        nome,
                        duracao_horas
                ));

            }

        } catch (SQLException e) {
            System.out.println("Lista de cursos FALHOU!");
            e.printStackTrace();
        }
        return cursos;

    }

    public Curso getById(int id) {

        Curso curso = new Curso();

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM curso WHERE id= ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracao_horas(rs.getTime("data_horas"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public void create(Curso curso) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO curso(nome, data_horas) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setTime(2, curso.getDuracao_horas());

            int rowsAffectted = stmt.executeUpdate();

            System.out.println("Insercão BEM SUCEDIDA!. Foi adicionada " + rowsAffectted + "linha");


        } catch (Exception e) {
            System.out.println("FALHA em adicionar curso");
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM curso id= ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDO! Foi deletado " + rowsAffected + "linha");


        } catch (Exception e) {
            System.out.println("delete FALHOU");
            e.printStackTrace();

        }

    }

    public void update(Curso curso) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "UPDATE curso SET nome=?, data_horas WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setTime(2, curso.getDuracao_horas());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA" + rowsAffected + "linha");

        } catch (Exception e) {

            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }

    }
}