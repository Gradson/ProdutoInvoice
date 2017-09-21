package br.com.gradson.atech;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;

import org.junit.runner.RunWith;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableSpringDataWebSupport
public class MockController {
	private static final MediaType APPLICATION_JSON = MediaType.APPLICATION_JSON;

	protected MockMvc mockMvc;
	
	public <T> void setUp(T controller) {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}

	public Type getParametrizedType() {

		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();

		return type.getActualTypeArguments()[0];
	}

	public MockHttpServletRequestBuilder post(final String endpoint) {
		return MockMvcRequestBuilders.post(URI.create(endpoint)).headers(getHeaders()).accept(APPLICATION_JSON);
  }

	public MockHttpServletRequestBuilder get(final String endpoint) {
		return MockMvcRequestBuilders.get(URI.create(endpoint)).headers(getHeaders()).accept(APPLICATION_JSON);
	}

	public MockHttpServletRequestBuilder delete(final String endpoint) {
		return MockMvcRequestBuilders.delete(URI.create(endpoint)).headers(getHeaders()).accept(APPLICATION_JSON);
	}

	public MockHttpServletRequestBuilder put(final String endpoint) {
		return MockMvcRequestBuilders.put(URI.create(endpoint)).headers(getHeaders()).accept(APPLICATION_JSON);
	}

	public <T> T toObject(final MvcResult result, final Class<T> clazz) throws Exception {
		return toObject(result.getResponse().getContentAsString(), clazz);
	}

	public <T> T toObject(final String json, final Class<T> clazz) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

	public byte[] toJson(final Object obj) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(obj);
	}

	public String toJsonAsString(final Object obj) throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(obj);
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", APPLICATION_JSON.toString());
		headers.add("Origin", "*");
		return headers;
	}
}