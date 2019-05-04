package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.VooDao;

@Controller
public class VooController {

	@Autowired
	private VooDao repositorio;
	
	final static Logger logger = LogManager.getLogger(VooController.class);

	@GetMapping("/")
	public String index(Model model) {
		atualizarBase();
		model.addAttribute("voos", voos());
		return "index";
	}

	private void atualizarBase() {
		try {
			Map o = ClientBuilder.newClient().target(System.getenv("AIRLOCATION_URL")).queryParam("lamin", System.getenv("AIRLOCATION_LAMIN")).queryParam("lomin", System.getenv("AIRLOCATION_LOMIN"))
					.queryParam("lamax", System.getenv("AIRLOCATION_LAMAX")).queryParam("lomax", System.getenv("AIRLOCATION_LOMAX")).request()
					.accept(MediaType.APPLICATION_JSON).get(Map.class);
			processa(o);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error("ERROR: " + e);
			throw new RuntimeException(e);
		}	
	}

	private List<Voo> voos() {
		return repositorio.findAll();
	}

	private void processa(final Map map) {

		final List<Voo> voos = new ArrayList<Voo>();
		final List lines = (List) map.get("states");
		for (int i = 0, l = lines.size(); i < l; i++) {
			try {
				final List line = (List)lines.get(i);
				voos.add(new Voo(line));
			}catch(Exception ex) {
				logger.error("ERROR: Erro ao inserir voos na lista: " + ex);
			}
		}
		try {
			repositorio.saveAll(voos);
		}
		catch(Exception ex) {
			logger.error("ERROR: icao24 já consta na base: " + ex);
		}
		logger.info("INFO: Dados gravados com sucesso!");
	}

}