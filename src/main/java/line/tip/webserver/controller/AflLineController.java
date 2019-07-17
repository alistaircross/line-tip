package line.tip.webserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import line.tip.data.AflGame;
import line.tip.data.AflRound;
import line.tip.extractor.shared.ExtractorParentService;
import line.tip.utils.RequestError;
import line.tip.webserver.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static line.tip.utils.RequestError.UNKNOWN_ERROR;

@RestController
public class AflLineController {
    private static Logger LOG = LogManager.getLogger(AflLineController.class);
    private ExtractorParentService extractorParentService;
    private ObjectMapper mapper;

    public AflLineController() {
        mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/getRound", method = RequestMethod.GET, produces = "application/json")
    public String requestRound(@RequestParam(required = false) Integer roundNumber) throws JsonProcessingException {
        if(roundNumber > 0 && roundNumber != null) {
            AflRound round = getExtractorParentService().getLadbrokesRounds().get(roundNumber);
            try {
                if(round == null) {
                    return mapper.writeValueAsString(RequestError.NO_ROUND);
                }
                return mapper.writeValueAsString(round);
            } catch (JsonProcessingException e) {
                LOG.error("Can't parse round to String");
                return mapper.writeValueAsString(UNKNOWN_ERROR);
            }
        }
        return mapper.writeValueAsString(UNKNOWN_ERROR);
    }

    @RequestMapping(value = "/getGame", method = RequestMethod.GET, produces = "application/json")
    public String requestGame(@RequestParam(required = false) Integer roundNumber, @RequestParam(required = false) Integer gameNumber) throws JsonProcessingException {
        if(roundNumber > 0 && roundNumber != null && gameNumber > 0 && gameNumber != null) {
            AflRound round = getExtractorParentService().getLadbrokesRounds().get(roundNumber);
            try {
                if(round == null) {
                    return mapper.writeValueAsString(RequestError.NO_ROUND);
                }
                AflGame game = round.getGames().get(gameNumber+1);
                return mapper.writeValueAsString(game);
            } catch (JsonProcessingException e) {
                LOG.error("Can't parse round to String");
                return mapper.writeValueAsString(UNKNOWN_ERROR);
            }
        }
        return mapper.writeValueAsString(UNKNOWN_ERROR);
    }

    private ExtractorParentService getExtractorParentService() {
        if(extractorParentService == null) {
            extractorParentService = Application.getExtractorParentService();
        }
        return extractorParentService;
    }
}
