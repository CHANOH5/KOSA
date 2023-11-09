let base = 100
const add = (x) => base+x // 화살표 함수를 사용하게 되면 자동리턴
                   // {base+x} 중괄호로 열고닫으면 계산된 결과만 나오고 리턴은 하지 않음
const test = () => {    
    console.log('module내부에서 this는 undefined입니다', this)
}

// 외부 자바스크립트 파일로 사용하고 노출하고 싶은 함수들을 export로 쓰면 된다
export {add, test }
