package by.kihtenkoolga.cache.handler;

import by.kihtenkoolga.cache.CacheValue;
import by.kihtenkoolga.model.User;
import by.kihtenkoolga.util.UserTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LRUCacheHandlerTest {

    private LRUCacheHandler<Long, User> lru;
    private User userId1 = UserTestBuilder.USERid1;
    private User userId2 = UserTestBuilder.USERid2;
    private User userId3 = UserTestBuilder.USERid3;
    private User userId4 = UserTestBuilder.USERid4;

    @BeforeEach
    void setUp() {
        lru = new LRUCacheHandler<>(3);
    }

    @Nested
    class Get {

        @Test
        void checkGetShouldBeEmpty() {
            assertThat(lru.getCacheBase()).isEmpty();
        }

        @Test
        void checkGetObjectWith2Use() {
            long uId = userId1.getId();
            lru.put(uId, userId1);

            lru.get(uId);

            assertThat(lru.getCacheBase().get(uId))
                    .isEqualTo(new CacheValue<>(1L, userId1));
        }

        @Test
        void checkGetByNull() {
            assertThat(lru.getCacheBase().get(null))
                    .isEqualTo(null);
        }
    }

    @Nested
    class Put {

        @Test
        void checkPut4ElementsWithCapacity3() {
            lru.put(userId1.getId(), userId1);
            lru.put(userId2.getId(), userId2);
            lru.put(userId3.getId(), userId3);
            lru.put(userId3.getId(), userId3);
            lru.put(userId4.getId(), userId4);

            assertThat(lru.getCacheBase().size())
                    .isEqualTo(3);
        }

        @Test
        void checkPutNullId() {
            lru.put(null, null);

            assertThat(lru.getCacheBase().size())
                    .isEqualTo(0);
        }

        @Test
        void checkPutNullValue() {
            lru.put(1L, null);

            assertThat(lru.getCacheBase().size())
                    .isEqualTo(0);
        }
    }

    @Nested
    class Remove {
        @Test
        void checkRemoveOne() {
            lru.put(userId1.getId(), userId1);
            lru.put(userId2.getId(), userId2);
            lru.put(userId3.getId(), userId3);

            lru.remove(userId2.getId());

            assertThat(lru.getCacheBase().size())
                    .isEqualTo(2);
        }

        @Test
        void checkAutoRemove() {
            lru.put(userId1.getId(), userId1);
            lru.get(userId1.getId());
            lru.put(userId2.getId(), userId2);
            lru.get(userId2.getId());
            lru.put(userId3.getId(), userId3);

            lru.put(userId4.getId(), userId4);

            assertThat(lru.getCacheBase().get(userId1.getId()))
                    .isNull();
        }
        @Test
        void checkGetObjectAfterAutoRemove() {
            lru.put(1l, userId1);
            lru.put(2l, userId2);
            lru.put(3l, userId3);
            lru.put(4l, userId4);


            assertThat(lru.getCacheBase().get(1L))
                    .isEqualTo(null);
        }

        @Test
        void checkRemoveByNull() {
            assertDoesNotThrow(() -> lru.remove(null));
        }
    }
}