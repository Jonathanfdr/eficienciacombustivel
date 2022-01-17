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
public class ModeloControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listaDevolveItens() throws Exception{
		URI uri = new URI("/modelo");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void detalhaIdValido() throws Exception{
		URI uri = new URI("/modelo/1");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroDetalhaIdInvalido() throws Exception{
		URI uri = new URI("/modelo/-5");
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void cadastroValido() throws Exception{
		URI uri = new URI("/modelo");
		String json = "{\"nome\":\"Gol 2\",\"idMarca\":5}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void erroCadastroNomeJaCadastrado() throws Exception{
		URI uri = new URI("/modelo");
		String json = "{\"nome\":\"Gol\",\"idMarca\":5}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void erroCadastroMarcaInvalida() throws Exception{
		URI uri = new URI("/modelo");
		String json = "{\"nome\":\"Chevette\",\"idMarca\":-1}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void erroCadastroCampoFaltante() throws Exception{
		URI uri = new URI("/modelo");
		String json = "{\"nome\":\"Gol 2\"}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		json = "{\"idMarca\":5}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void atualizacaoValida() throws Exception{
		URI uri = new URI("/modelo/1");
		String json = "{\"nome\":\"Chevette\",\"idMarca\":1}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
		json = "{\"nome\":\"Doblo\",\"idMarca\":4}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroAtualizacaoNomeExistenteComIdDiferente() throws Exception{
		URI uri = new URI("/modelo/1");
		String json = "{\"nome\":\"500\",\"idMarca\":4}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void atualizacaoValidaNomeExistenteMesmoId() throws Exception{
		URI uri = new URI("/marca/1");
		String json = "{\"nome\":\"Onix\",\"idMarca\":4}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroAtualizacaoMarcaInvalida() throws Exception{
		URI uri = new URI("/modelo/1");
		String json = "{\"nome\":\"Onix\",\"idMarca\":-1}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri)
			    .content(json)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void deletaIdValido() throws Exception{
		URI uri = new URI("/modelo/2");
		mockMvc.perform(MockMvcRequestBuilders
			    .delete(uri)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void erroDeletaIdInvalido() throws Exception{
		URI uri = new URI("/modelo/-5");
		mockMvc.perform(MockMvcRequestBuilders
			    .delete(uri)
			    .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
}
