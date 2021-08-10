import java.util.List;

public class QueriesExecute {

    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();

        CursoDao cursoDao = new CursoDao();

        // =========================== 1 - Consulta =================================================
        List<Aluno> alunos = alunoDAO.list();

        alunos.stream().forEach(System.out::println);

        List<Curso> cursos = cursoDao.list();


        // ======================= 1.1 - Consulta com filtro ========================================
       // Aluno alunoParaConsulta = alunoDAO.getById(1);

        //System.out.println(alunoParaConsulta);


        // =========================== 2 - Inserção =================================================
        Aluno alunoParaInsercao = new Aluno(
                "Matheus",
                43,
                "SP"
        );
        //alunoDAO.create(alunoParaInsercao);




        // =========================== 3 - Delete ===================================================
        //alunoDAO.delete(1);


        // =========================== 4 - Atualizar ================================================
        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("Paulo Ricardo");
        alunoParaAtualizar.setIdade(24);
        alunoParaAtualizar.setEstado("MG");

        alunoDAO.update(alunoParaAtualizar);
    }

}
