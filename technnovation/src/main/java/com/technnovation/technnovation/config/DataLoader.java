package com.technnovation.technnovation.Config;

import com.technnovation.technnovation.Model.*;
import com.technnovation.technnovation.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final GroupRepository groupRepository;
    private final NotificationRepository notificationRepository;

    public DataLoader(UserRepository userRepository,
                      NewsRepository newsRepository,
                      GroupRepository groupRepository,
                      NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.groupRepository = groupRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Si ya hay usuarios, no hacemos nada para no duplicar
        if (userRepository.count() > 0) return;

        System.out.println("--- CARGANDO DATOS EN SPRING BOOT ---");

        // 1. USUARIOS
        User admin = User.builder()
                .nombre("Admin System")
                .correo("admin@technovation.com")
                .contrasenaHash("123456")
                .rol("ADMIN")
                .ubicacion("Nube")
                .pais("Internet")
                .avatarUrl("https://ui-avatars.com/api/?name=Admin")
                .build();
        userRepository.save(admin);

        // 2. NOTICIAS
        News n1 = News.builder()
                .titulo("GPT-4o Lanzado")
                .descripcion("OpenAI lanza su nuevo modelo insignia.")
                .fuente("OPEN_AI")
                .imageUrl("https://images.unsplash.com/photo-1677442136019-21780ecad995?auto=format&fit=crop&w=800&q=80")
                .urlInfo("https://openai.com")
                .fechaPublicacion(LocalDateTime.now())
                .build();

        News n2 = News.builder()
                .titulo("Android 15 Beta")
                .descripcion("Nuevas funciones de privacidad llegan a Android.")
                .fuente("GOOGLE")
                .imageUrl("https://images.unsplash.com/photo-1620712943543-bcc4688e7485?auto=format&fit=crop&w=800&q=80")
                .urlInfo("https://android.com")
                .fechaPublicacion(LocalDateTime.now())
                .build();
        newsRepository.saveAll(Arrays.asList(n1, n2));

        // 3. GRUPOS
        Group g1 = Group.builder()
                .nombre("IA_LAB")
                .descripcion("Investigación de IA.")
                .miembros(Arrays.asList("admin"))
                .estado("ABIERTO")
                .build();
        groupRepository.save(g1);

        System.out.println("--- DATOS CARGADOS ---");
    }
}