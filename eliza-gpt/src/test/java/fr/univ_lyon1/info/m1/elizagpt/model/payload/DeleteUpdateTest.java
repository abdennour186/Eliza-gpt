package fr.univ_lyon1.info.m1.elizagpt.model.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class DeleteUpdateTest {
    private DeleteUpdate deleteUpdate;
    @Test
    void getDeletedMessageId() {
        int deletedMessageId = 3;
        deleteUpdate = new DeleteUpdate(deletedMessageId);

        int result = deleteUpdate.getDeletedMessageId();

        assertSame(deletedMessageId, result);
    }
}
