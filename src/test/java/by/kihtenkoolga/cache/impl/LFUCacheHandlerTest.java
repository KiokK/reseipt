package by.kihtenkoolga.cache.impl;

import by.kihtenkoolga.cache.CacheValue;
import by.kihtenkoolga.model.User;
import by.kihtenkoolga.util.UserTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LFUCacheHandlerTest {

    private LFUCacheHandler<Long, User> lfu;
    private User userId1 = UserTestBuilder.USERid1;
    private User userId2 = UserTestBuilder.USERid2;
    private User userId3 = UserTestBuilder.USERid3;
    private User userId4 = UserTestBuilder.USERid4;

    @BeforeEach
    void setUp() {
        lfu = new LFUCacheHandler<>(3);
    }

    @Nested
    class Get {
        @Test
        void checkGetShouldBeEmpty() {
            assertThat(lfu.getCacheBase()).isEmpty();
        }

        @Test
        void checkGetObjectWith2Use() {
            long uId = userId1.getId();
            lfu.put(uId, userId1);

            lfu.get(uId);

            assertThat(lfu.getCacheBase().get(uId))
                    .isEqualTo(new CacheValue<>(2L, userId1));
        }

        @Test
        void checkGetByNull() {
            assertThat(lfu.getCacheBase().get(null))
                    .isEqualTo(null);
        }
    }

    @Nested
    class Put {
        @Test
        void checkPut3Elements() {
            lfu.put(userId1.getId(), userId1);
            lfu.put(userId2.getId(), userId2);
            lfu.put(userId3.getId(), userId3);
            lfu.put(userId3.getId(), userId3);
            lfu.put(userId4.getId(), userId4);

            assertThat(lfu.getCacheBase().size())
                    .isEqualTo(3);
        }

        @Test
        void checkPutNullId() {
            lfu.put(null, null);

            assertThat(lfu.getCacheBase().size())
                    .isEqualTo(0);
        }

        @Test
        void checkPutNullValue() {
            lfu.put(1L, null);

            assertThat(lfu.getCacheBase().size())
                    .isEqualTo(0);
        }
    }

    @Nested
    class Remove {
        @Test
        void checkRemoveOne() {
            lfu.put(userId1.getId(), userId1);
            lfu.put(userId2.getId(), userId2);
            lfu.put(userId3.getId(), userId3);

            lfu.remove(userId2.getId());

            assertThat(lfu.getCacheBase().size())
                    .isEqualTo(2);
        }

        @Test
        void checkAutoRemove() {
            lfu.put(userId1.getId(), userId1);
            lfu.get(userId1.getId());
            lfu.put(userId2.getId(), userId2);
            lfu.get(userId2.getId());
            lfu.put(userId3.getId(), userId3);

            lfu.put(userId4.getId(), userId4);

            assertThat(lfu.getCacheBase().get(userId3.getId()))
                    .isNull();
        }

        @Test
        void checkRemoveByNull() {
            assertDoesNotThrow(() -> lfu.remove(null));
        }
    }
}
