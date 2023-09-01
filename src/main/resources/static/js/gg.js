let d1;
function display2() {
     d1 = new Date(form2.doa1.value);

    let table = document.getElementById('1')
    let tBody = table.querySelector('tbody')
    let ths = tBody.querySelectorAll('th')

    let counter = 0
    for (let i = 0; i < ths.length / 7; i++) {
        const currentDateCol = 6
        const colsPerRow = 7
        let d2= ths[i * colsPerRow + currentDateCol].innerText
        let currentDate = new Date(d2)

        if (currentDate.getTime() === d1.getTime()) {
            counter++
        }
    }
    document.querySelector('.asd2').innerHTML = counter
}