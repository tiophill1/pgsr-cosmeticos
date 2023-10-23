 package com.comesticos.tcc.pgsrcosmeticos.controller;

 

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

 

import com.comesticos.tcc.pgsrcosmeticos.model.Cliente;
import com.comesticos.tcc.pgsrcosmeticos.repository.ClienteRepository;

 

@Controller
@RequestMapping("/pgsr/cliente")
public class ClienteController {
	List<Cliente> listCliente = new ArrayList<Cliente>();

	@Autowired //Injeção de dependência
	private ClienteRepository clienteRepository;
	
	@GetMapping("/listar-cliente")
	public String tabelaClientes(Model model, Cliente cliente) {
		model.addAttribute("cliente", clienteRepository.findAll());
		return "crud";
	}
	
    @GetMapping("/novo-cliente")
    public String litarProduto(Model model, Cliente cliente) {

        model.addAttribute("cliente",cliente);
        return "Cadastro_cliente";

    }


    
//Cadastrar (inserir)

	@PostMapping("/add-cliente")
	public String addCliente(Cliente cliente, BindingResult result, Model model){
		if(result.hasErrors()) {
			return "Cadastro_cliente";
		}
		
		Cliente clienteDB = clienteRepository.save(cliente);
		cliente.setCodStatus(true);
		
		return "redirect:/pgsr/cliente/control";
	}
	
    
}
