package lpm.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Cliente {
    private String id;
    private String nome;
    private ArrayList<Veiculo> veiculos;

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Cliente(String id, String nome, ArrayList<Veiculo> veiculos) {
        this.id = id;
        this.nome = nome;
        this.veiculos = veiculos;
    }

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.veiculos = new ArrayList<Veiculo>();
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
        if (!veiculos.contains(veiculo)) {
            veiculos.add(veiculo);
        } else {
            throw new Error("Erro: veiculo ja cadastrado para esse cliente");
        }
    }

    public Veiculo possuiVeiculo(String placa) {
        Iterator<Veiculo> iterator = veiculos.iterator();
        Veiculo aux;

        while (iterator.hasNext()) {
            aux = iterator.next();
            if (aux.getPlaca().equalsIgnoreCase(placa)) {
                return aux;
            }
        }

        return null;
    }

    public int totalDeUsos() {
        Iterator<Veiculo> iterator = veiculos.iterator();
        int result = 0;

        while (iterator.hasNext()) {
            result = result + iterator.next().totalDeUsos();
        }

        return result;
    }

    public double arrecadadoPorVeiculo(String placa) {
        return possuiVeiculo(placa).totalArrecadado();
    }

    public double arrecadadoTotal() {
        Iterator<Veiculo> iterator = veiculos.iterator();
        double result = 0.0;

        while (iterator.hasNext()) {
            result = result + iterator.next().totalArrecadado();
        }

        return result;
    }

    public double arrecadadoNoMes(int mes) {
        Iterator<Veiculo> iterator = veiculos.iterator();
        double result = 0.0;

        while (iterator.hasNext()) {
            result = result + iterator.next().arrecadadoNoMes(mes);
        }

        return result;
    }

    public void printVeiculos() {
        Iterator<Veiculo> iterator = veiculos.iterator();
        Veiculo aux;

        while (iterator.hasNext()) {
            aux = iterator.next();
            System.out.println("Placa: " + aux.getPlaca());
            System.out.println("Total de usos: " + aux.totalDeUsos());
            System.out.println("Total arrecadado: " + aux.totalArrecadado());
            System.out.println("Arrecadado no mes: " + aux.arrecadadoNoMes(11));
        }
    }
}
