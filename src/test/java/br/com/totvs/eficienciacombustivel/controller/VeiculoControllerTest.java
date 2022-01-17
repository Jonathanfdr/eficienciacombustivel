package br.com.totvs.eficienciacombustivel.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VeiculoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listaDevolveItens() throws Exception{
		URI uri = new URI("/veiculo");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void detalhaIdValido() throws Exception{
		URI uri = new URI("/veiculo/3");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroDetalhaIdInvalido() throws Exception{
		URI uri = new URI("/veiculo/-5");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void cadastroValido() throws Exception{
		URI uri = new URI("/veiculo");
		String json = "{\"nome\":\"HAM-7153\",\"idModelo\":3,\"anoFabricacao\":2021,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isCreated());
		json = "{\"nome\":\"HAM-7154\",\"idModelo\":3,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void erroCadastroNomeJaCadastrado() throws Exception{
		URI uri = new URI("/veiculo");
		String json = "{\"nome\":\"JZN-7955\",\"idModelo\":3,\"anoFabricacao\":2021,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void erroCadastroModeloInvalido() throws Exception{
		URI uri = new URI("/veiculo");
		String json = "{\"nome\":\"HAM-7159\",\"idModelo\":-1,\"anoFabricacao\":2021,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void erroCadastroCampoFaltante() throws Exception{
		URI uri = new URI("/veiculo");
		
		String json = "{\"idModelo\":3,\"anoFabricacao\":2021,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		json = "{\"nome\":\"HAM-7153\",\"anoFabricacao\":2021,\"consumoCidade\":10,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		json = "{\"nome\":\"HAM-7153\",\"idModelo\":3,\"anoFabricacao\":2021,\"consumoRodovia\":15}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		json = "{\"nome\":\"HAM-7153\",\"idModelo\":3,\"anoFabricacao\":2021,\"consumoCidade\":10}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void atualizacaoValida() throws Exception{
		URI uri = new URI("/veiculo/1");
		String json = "{\"nome\":\"JZN-7955\",\"idModelo\":21,\"anoFabricacao\":2019,\"consumoCidade\":11.9,\"consumoRodovia\":14.3}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
		json = "{\"nome\":\"JZN-7956\",\"idModelo\":22,\"anoFabricacao\":2019,\"consumoCidade\":11.9,\"consumoRodovia\":14.3}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroAtualizacaoNomeExistenteComIdDiferente() throws Exception{
		URI uri = new URI("/veiculo/1");
		String json = "{\"nome\":\"NAA-3389\",\"idModelo\":22,\"anoFabricacao\":2019,\"consumoCidade\":11.9,\"consumoRodovia\":14.3}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void erroAtualizacaoModeloInvalida() throws Exception{
		URI uri = new URI("/veiculo/1");
		String json = "{\"nome\":\"JZN-7955\",\"idModelo\":-22,\"anoFabricacao\":2021,\"consumoCidade\":11.9,\"consumoRodovia\":1}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void deletaIdValido() throws Exception{
		URI uri = new URI("/veiculo/1");
		mockMvc.perform(MockMvcRequestBuilders
			    .delete(uri)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroDeletaIdInvalido() throws Exception{
		URI uri = new URI("/veiculo/-5");
		mockMvc.perform(MockMvcRequestBuilders
			    .delete(uri)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void listaRankingEficiencia() throws Exception{
		URI uri = new URI("/veiculo/rankingEficiencia");
		String json = "{\"precoCombustivel\":5,\"distanciaPercorridaCidade\":100,\"distanciaPercorridaRodovia\":150}";
		mockMvc.perform(MockMvcRequestBuilders
			    .get(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroListaRankingEficienciaCampoFaltante() throws Exception{
		URI uri = new URI("/veiculo/rankingEficiencia");
		
		String json = "{\"distanciaPercorridaCidade\":100,\"distanciaPercorridaRodovia\":150}";
		mockMvc.perform(MockMvcRequestBuilders
			    .get(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		json = "{\"precoCombustivel\":5,\"distanciaPercorridaRodovia\":150}";
		mockMvc.perform(MockMvcRequestBuilders
			    .get(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		json = "{\"precoCombustivel\":5,\"distanciaPercorridaCidade\":100}";
		mockMvc.perform(MockMvcRequestBuilders
			    .get(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
