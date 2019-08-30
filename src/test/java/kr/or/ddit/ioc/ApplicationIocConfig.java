package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class ApplicationIocConfig {
//자바로 bean 만들기
	//<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDao"/>
	@Bean
	public IboardDao boardDao() {
		return new BoardDao();
	}
	
//	<bean id="boardService" class="kr.or.ddit.board.service.BoardService">
//	<!-- boardService의 field, property -->
//	<!-- setter injection -->
//	<property name="boardDao" ref="boardDao"/>
//	<property name="n" value="brown">
//	</bean>
	
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		
		return boardService;
	}
}
