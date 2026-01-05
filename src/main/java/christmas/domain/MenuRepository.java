package christmas.domain;

import java.util.*;

public class MenuRepository {
    private final Map<String, Menu> storage = new LinkedHashMap<>();

    // 1. 저장
    public void save(Menu item) {
        // validateDuplicate(item); // 필요 시 중복 검사
        storage.put(item.getName(), item);
    }

    // 2. 단건 조회 (Optional 추천)
    public Optional<Menu> findByName(String name) {
        return Optional.ofNullable(storage.get(name));
    }

    // 3. 전체 조회 (불변 리스트 반환 추천)
    public List<Menu> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(storage.values()));
    }

    // 4. 삭제 (필요 시)
    public void deleteByName(String name) {
        storage.remove(name);
    }

    // 5. 초기화 (테스트 시 필수!)
    public void clear() {
        storage.clear();
    }
}
