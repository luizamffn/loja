package br.com.alura.microservice.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.microservice.loja.DTO.InfoFornecedorDTO;
import br.com.alura.microservice.loja.DTO.InfoPedidoDTO;
import br.com.alura.microservice.loja.DTO.ItemDaCompraDTO;

@FeignClient("fornecedor") //id do microservico que a aplicação irá acessar  
							//Adicionar o @EnableFeignClients no LojaApplication
public interface FornecedorClient {

	@RequestMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

	@RequestMapping(method = RequestMethod.POST, value =  "/pedido")
	InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}
