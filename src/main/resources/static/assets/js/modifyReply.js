import { BASE_URL } from "./reply.js";
import { fetchInfScrollReplies } from "./getReply.js";

// 수정 이벤트 등록 함수
export function modifyReplyClickEvent () {

  // 수정모드

  document.getElementById('replyData').addEventListener('click', e => {
    // 링크 기능 제거
    e.preventDefault();

    // 버블링 건 상태이기 때문에 타겟 제한해주기
    if (!e.target.matches ('#replyModBtn')) return;

    // 수정버튼에 매치되는 댓글 찾기
    // console.log(e.target.closest('.row').firstElementChild.textContent);

    // 수정 전 텍스트 읽기
    const replyText = e.target.closest('.row').firstElementChild.textContent;

    // 모달의 textArea에 수정 전 텍스트 넣기
    document.getElementById('modReplyText').value = replyText;

    // 댓글 번호 구하기
    const rno = e.target.closest('#replyContent').dataset.replyId;

    // 모달에 클릭한 댓글번호 달아놓기
    document.querySelector('.modal').dataset.rno = rno;
  })

  // 수정 요청 처리 이벤트
  document.getElementById('replyModBtn').addEventListener('click', e => {
    fetchReplyModify();
  })

  async function fetchReplyModify () {

    // 수정 시 보낼 데이터
    const payload = {
      rno: document.querySelector('.modal').dataset.rno,
      newText: document.getElementById('modReplyText').value,
      bno: document.getElementById('wrap').dataset.bno
    };

    console.log(payload);

    // 페치보내기
    const res = await fetch (BASE_URL, {
      method: 'PUT',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    if (!res.ok) {
      alert('수정 실패!');
    }

    // 모달 닫기
    document.getElementById('modal-close').click();

    window.scrollTo(0, 500) // 수정 후 페이지 상단으로 이동
    await fetchInfScrollReplies();
  }
}