package com.unisound.vui.message;

public interface TransferHandlerWithGui {
    void preReceiveMsg(MessageBeanHandlerGui<?> messageBeanHandlerGui);

    void receiveMsg(MessageBeanHandlerGui<?> messageBeanHandlerGui);

    void registerMessageHandlerWithGui(TransferHandlerWithGui transferHandlerWithGui);

    void sendMsg(MessageBeanHandlerGui<?> messageBeanHandlerGui, TransferHandlerWithGui transferHandlerWithGui);
}
