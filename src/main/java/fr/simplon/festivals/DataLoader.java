package fr.simplon.festivals;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.simplon.festivals.dao.impl.FestivalRepository;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class DataLoader implements ApplicationRunner
{
    private final FestivalRepository FestivalRepository;
    @Autowired
    public DataLoader(FestivalRepository festivalRepository)
    {
        this.FestivalRepository = festivalRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if (FestivalRepository.count() == 0)
        {
            ClassPathResource resource = new ClassPathResource("static/festivals.json");

            ObjectMapper objectMapper = new ObjectMapper();
            List<Festival> festivals = objectMapper.readValue(
                    resource.getInputStream(), new TypeReference<List<Festival>>(){});
            FestivalRepository.saveAll(festivals);
        }
    }
}