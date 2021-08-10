
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    // 1 - Consulta

    public List<Aluno> list() {
        // Preparar lista que irá retornar alunos após consultar o banco de dados

        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {
            //Preparar consulta SQL.
            String sql = "SELECT * FROM aluno";

            //Preparar  statement com os parâmetros recebidos (nesta função não tem parâmetros, pois irá retornar todos os valores da tabela aluno)
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Executar consulta e armazena o retorno da consulta no objeto "rs".
            ResultSet rs = stmt.executeQuery();

            //Criar um objeto aluno e guardar na lista de alunos

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String estado = rs.getString("estado");

                alunos.add(new Aluno(
                        id,
                        nome,
                        idade,
                        estado

                ));

            }

        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        //Retorna todos os alunos encontrados no banco de dados.
        return alunos;
    }

    // 1.1 - Consulta com filtro
    public Aluno getById(int id) {
        //Prepara objeto aluno para receber os valores do banco de dados

        Aluno aluno = new Aluno();

        try (Connection conn = ConnectionFactory.getConnection()) {
            // Preparar consulta SQL
            String sql = "SELECT * FROM aluno WHERE id = ?";

            //Preparar stament com os parâmetro recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executar consulta e armazenar o retorno da consulta no objeto "rs"
            ResultSet rs = stmt.executeQuery();

            // Guardar valores retornados da tabela aluno no objeto aluno

            if (rs.next()) {
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));

            }


        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        //Retorna aluno encontrado no banco de dados
        return aluno;
    }

    // 2 - Inserção
    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            // Preparar SQL para inserção de dados do aluno.
            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)";

            // Preparar stamente com parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());


            //Executar inserção e armazena o numero de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");

        } catch (SQLException e) {
            System.out.println("Inserção de curso FALHOU!");
            e.printStackTrace();
        }
    }

    // 3 - Delete
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para deletar uma linha
            String sql = "DELETE FROM aluno id = ?";

            // Preparar stament com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            // Executar delete e armazena o numero de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDO! Foi deletada" + rowsAffected + "linha");


        } catch (SQLException e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
        }
    }

    // 4 - Atualizar
    public void update(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para atualizar linhas.
            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            //Prepara statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4, aluno.getId());

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada " + rowsAffected + " linha ");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }


    }

}