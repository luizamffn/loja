package br.com.alura.microservice.loja.service;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.DTO.CompraDTO;
import br.com.alura.microservice.loja.DTO.InfoFornecedorDTO;
import br.com.alura.microservice.loja.DTO.InfoPedidoDTO;
import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.modelo.Compra;
import ch.qos.logback.classic.Logger;

@Service
public class CompraService {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(CompraService.class);
			
	
	//tem que declarar o @EnableFeignClients na LojaApplication
	@Autowired
	private FornecedorClient  fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compraDTO) {
		LOG.info("buscando informacoes do fornecedor de {} ", compraDTO.getEndereco().getEstado());
		InfoFornecedorDTO infoFornecedorDTO =  fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

		LOG.info("relizando pedido");
		InfoPedidoDTO pedidoDTO =  fornecedorClient.realizaPedido(compraDTO.getItens());
		
		System.out.println(infoFornecedorDTO.getEndereco());
		
		Compra compra = new Compra();
		compra.setId(pedidoDTO.getId());
		compra.setTempoDePreparo(pedidoDTO.getTempoDePreparo());
		compra.setEnderecoDestino(compraDTO.getEndereco().toString());
		
		return compra;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private RestTemplate client;
//	
//	@Autowired
//	private DiscoveryClient eurekaClient;
//
//	public void realizaCompra(CompraDTO compraDTO) {
//		//o nome fornecedor é substituido pelo endereco ip da aplicacao do fornecedor
//		ResponseEntity<InfoFornecedorDTO> exchange = 	
//					client.exchange("http://fornecedor/info/"+compraDTO.getEndereco().getEstado(), 
//							HttpMethod.GET, null, InfoFornecedorDTO.class);
//		
//		eurekaClient.getInstances("fornecedor").stream().forEach(
//				fornecedor -> {
//					System.out.println("localhost: " +fornecedor.getPort());
//				});
//		
//		System.out.println(exchange.getBody().getEndereco());
//	}

}
