package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {

    // @HandlesTypes에 등록한 인터페이스를 구현한 구현체들을 찾아서 그 클래스들을 Set<Class<?>> c 파라미터에 담아서 보내준다.
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV1.onStartup");
        System.out.println("c = " + c);
        System.out.println("ctx = " + ctx);

        // hello.container.AppInitV1Servlet
        for (Class<?> appInitClass : c) {
            try {
                // new AppInitV1Servlet() 과 같은 코드
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
