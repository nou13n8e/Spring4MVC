// list
let newbtn=document.querySelector("#newbtn")
    newbtn?.addEventListener('click',()=>{
        location.href='/board/write';
    })

let newbdbtn=document.querySelector("#newbdbtn")
    newbdbtn?.addEventListener('click', ()=>{
        let frm=document.forms.writefrm;
            if(grecaptcha.getResponse()==='') { alert('자동작성방지를 확인하세요.') }
            else { alert('작성 완료') };
    })