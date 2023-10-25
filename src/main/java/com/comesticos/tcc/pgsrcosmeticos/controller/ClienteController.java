 package com.comesticos.tcc.pgsrcosmeticos.controller;

 

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.comesticos.tcc.pgsrcosmeticos.model.Cliente;
import com.comesticos.tcc.pgsrcosmeticos.repository.ClienteRepository;

 

@Controller
@RequestMapping("/pgsr/cliente")
public class ClienteController {
	List<Cliente> listCliente = new ArrayList<Cliente>();

	@Autowired //Injeção de dependência
	private ClienteRepository clienteRepository;
	
	@GetMapping("/listar-cliente")
	public String tabelaClientes(Cliente cliente, Model model) {
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
		
		return "redirect:/pgsr/cliente/listar-cliente";
	}
	
	@GetMapping("/editar-prod/{id}")
	public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid prod Id:" + id));
	


		model.addAttribute("cliente", cliente);
		
		return "Editar";
	}

	@PostMapping("/update/{id}")
	public String atualizarProd(
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") Long id, 
			@ModelAttribute("prod") Cliente cliente, BindingResult result) {

		if (result.hasErrors()) {
			cliente.setId(id);
			return "Editar";
		}
		

		clienteRepository.save(cliente);
		return "redirect:/pgsr/cliente/listar-cliente";
	}
	
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Cliente cliente, @PathVariable("id") Long id) {
		clienteRepository.deleteById(id);
		return "redirect:/pgsr/cliente/listar-cliente";
	}
    
}
