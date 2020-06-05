package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import entities.enums.WorkerLevel;

public class Worker {
	//**Atributos b�sicos**
	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	
	//**Associa��es**
	private Department department;
	//Utilizado a lista pois podem haver v�rios contratos
	private List<HourContract> contracts = new ArrayList<>();
	//Quando houver uma associa��o "um para muito (tem muitos)", n�o se deve incluir o atributo no construtor
	//Tem que apenas iniciar a lista, instanciar ela como vazia
	
	public Worker() {
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	//public void setContracts(List<HourContract> contracts) -> removido pois n�o queremos que outro contrato seja
	//adicionado aos nossos contratos
	
	//public void setContracts(List<HourContract> contracts) {
	//	this.contracts = contracts;
	//}	
	
	//-- M�TODOS --
	//objeto do tipo HourContract chamadado contract
	public void addContract(HourContract contract) {		
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	//M�TODO
	public double income(int year, int month) {
		double sum = baseSalary;
		//calend�rio instanciado para set utilizado no for each
		Calendar cal = Calendar.getInstance();
		for (HourContract c: contracts) {
			//A data do contrato est� sendo atribu�da a data do calend�rio
			cal.setTime(c.getDate());
			int c_year = cal.get(Calendar.YEAR);
			int c_month = 1 + cal.get(Calendar.MONTH);
			int teste = c.getHours();
			if (year == c_year && month == c_month) {
				//totalValue � um m�todo da classe HourContrac, respons�vel por calcular o sal�rio a ser pago 
				//no contrato. M�todo: return valuePerHour * hours;
				sum += c.totalValue();
			}
		}
		return sum;
	}
}
