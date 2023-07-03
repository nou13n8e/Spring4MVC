// list
let newbtn=document.querySelector("#newbtn")
    newbtn?.addEventListener('click',()=>{
        location.href='/board/write';
    })

let newbdbtn=document.querySelector("#newbdbtn")
    newbdbtn?.addEventListener('click', ()=>{
        let frm=document.forms.writefrm;
            if(frm.title.value==='') alert('제목을 작성하십시오.')
            else if(frm.contents.value==='') alert('본문을 작성하십시오.')
            else if(grecaptcha.getResponse()==='') alert('자동작성방지를 확인하세요.')
            else {
                frm.method='post';
                frm.submit();
                alert('작성 완료');
            }
    });