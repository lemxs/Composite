import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface DepartamentoComponente {
    double getCusto();
    int getFuncionarios();
}

class Departamento implements DepartamentoComponente {
    private String nome;
    private double custo;
    private int funcionarios;

    public Departamento(String nome, double custo, int funcionarios) {
        this.nome = nome;
        this.custo = custo;
        this.funcionarios = funcionarios;
    }

    @Override
    public double getCusto() {
        return custo;
    }

    @Override
    public int getFuncionarios() {
        return funcionarios;
    }

    @Override
    public String toString() {
        return nome + " (Custo: R$ " + custo + ", Funcionários: " + funcionarios + ")";
    }
}

// Composto
class Diretoria implements DepartamentoComponente {
    private List<DepartamentoComponente> departamentos = new ArrayList<>();

    public void adicionarDepartamento(DepartamentoComponente departamento) {
        departamentos.add(departamento);
    }

    @Override
    public double getCusto() {
        return departamentos.stream().mapToDouble(DepartamentoComponente::getCusto).sum();
    }

    @Override
    public int getFuncionarios() {
        return departamentos.stream().mapToInt(DepartamentoComponente::getFuncionarios).sum();
    }

    @Override
    public String toString() {
        return departamentos.stream().map(DepartamentoComponente::toString).collect(Collectors.joining(", "));
    }
}

// Exemplo de uso
public class Main {
    public static void main(String[] args) {
        Departamento rh = new Departamento("Recursos Humanos", 50000, 10);
        Departamento ti = new Departamento("Tecnologia da Informação", 150000, 25);
        Departamento vendas = new Departamento("Vendas", 100000, 20);

        Diretoria diretoria = new Diretoria();
        diretoria.adicionarDepartamento(rh);
        diretoria.adicionarDepartamento(ti);
        diretoria.adicionarDepartamento(vendas);

        System.out.println("Departamentos na diretoria: " + diretoria);
        System.out.println("Custo total: R$ " + diretoria.getCusto());
        System.out.println("Total de funcionários: " + diretoria.getFuncionarios());
    }
}
