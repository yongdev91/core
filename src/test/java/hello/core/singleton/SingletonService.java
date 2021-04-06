package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어봐서 객체 인스턴스가 필요하면 statuc 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 new생성을 막기 위해 private 생성자를 만들었다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
