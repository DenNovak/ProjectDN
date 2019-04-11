package Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/api")
public class MainController {
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping(path="/addcoach")
    public @ResponseBody String addNewCoach (
            @RequestParam String name,
            @RequestParam String adress,
            @RequestParam String surname) {

        Coach n = new Coach();
        n.setName(name);
        n.setSurname(surname);
        n.setAdress(adress);
        coachRepository.save(n);
        return "Saved Coach";
    }


    @GetMapping(path="/addclient")
    public @ResponseBody String addNewNote (
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer age,
            @RequestParam Integer coach_id) {

        Client c = new Client();
        c.setName(name);
        c.setSurname(surname);
        c.setAge(age);


        Optional<Coach> u = Optional.of(new Coach());
        u = coachRepository.findById(coach_id);

        u.get().addClient(c);

        coachRepository.save(u.get());

        return "Saved client";
    }

    @GetMapping(path="/delcoach")
    public @ResponseBody String deleteCoach (@RequestParam Integer coach_id) {
        Optional<Coach> u = Optional.of(new Coach());
        u = coachRepository.findById(coach_id);
        coachRepository.delete(u.get());
        return "Deleted";
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Coach> getAllUsers() {

        return coachRepository.findAll();
    }

    @GetMapping(path="/stats")
    public @ResponseBody Integer getStats() {
        return clientRepository.countClients();
    }


}
