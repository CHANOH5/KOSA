const base = 100
export default  {
    add : (x) => base+ x,
    multiply :  (x) => base * x,
    test : ()=> console.log('module내부에서 this는 undefined입니다', this)
}
