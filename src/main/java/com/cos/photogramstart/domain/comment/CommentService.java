package com.cos.photogramstart.domain.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	@Transactional
	public Comment 댓글쓰기() {
		return null;
	}
	@Transactional
	public void 댓글삭제() {
		
	}
}
