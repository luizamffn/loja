package br.com.alura.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alura.microservice.loja.DTO.CompraDTO;
import br.com.alura.microservice.loja.DTO.InfoFornecedorDTO;

@Service
public class CompraService {
	
	@Autowired
	private RestTemplate client;

	public void realizaCompra(CompraDTO compraDTO) {
		//o nome fornecedor é substituido pelo endereco ip da aplicacao do fornecedor
		ResponseEntity<InfoFornecedorDTO> exchange = 	
					client.exchange("http://fornecedor/info/"+compraDTO.getEndereco().getEstado(), 
							HttpMethod.GET, null, InfoFornecedorDTO.class);
		
		System.out.println(exchange.getBody().getEndereco());
	}

}
