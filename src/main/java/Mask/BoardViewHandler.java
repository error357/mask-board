package Mask;

import Mask.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BoardViewHandler {


    @Autowired
    private BoardRepository boardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPut_then_CREATE_1 (@Payload Put put) {
        try {
            if (put.isMe()) {

                if(boardRepository.findByMaskType(put.getMaskType()) == null) {
                    // view 객체 생성
                    Board board = new Board();
                    // view 객체에 이벤트의 Value 를 set 함
                    board.setMaskType(put.getMaskType());
                    board.setQty(put.getQty());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                } else {
                    List<Board> boardList = boardRepository.findByMaskType(put.getMaskType());
                    for (Board board : boardList) {
                        // view 객체에 이벤트의 eventDirectValue 를 set 함
                        board.setQty(put.getQty() + board.getQty());
                        // view 레파지 토리에 save
                        boardRepository.save(board);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_UPDATE_1(@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 조회
                List<Board> boardList = boardRepository.findByMaskType(ordered.getMaskType());
                for(Board board : boardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setQty(board.getQty() - ordered.getQty());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelled_then_UPDATE_2(@Payload Cancelled cancelled) {
        try {
            if (cancelled.isMe()) {
                // view 객체 조회
                List<Board> boardList = boardRepository.findByMaskType(cancelled.getMaskType());
                for (Board board : boardList) {
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setQty(board.getQty() + cancelled.getQty());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_3(@Payload Returned returned) {
        try {
            if (returned.isMe()) {
                // view 객체 조회
                List<Board> boardList = boardRepository.findByMaskType(returned.getMaskType());
                for (Board board : boardList) {
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    board.setQty(board.getQty() + returned.getQty());
                    // view 레파지 토리에 save
                    boardRepository.save(board);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}