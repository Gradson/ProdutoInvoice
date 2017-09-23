package br.com.gradson.atech.jms;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class JmsErrorHandler implements ErrorHandler {

	@Override
	public void handleError(Throwable t) {
		
		log.info("Error jms");
		// implementar padrão ainda
	}

}
