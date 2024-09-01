var x = ["Đã tích lũy", "Chưa tích lũy"];
var y = [piec[0],piec[1]];
var pieColors = ["#12CAD6","#FA163F"];
var mes =  String(y[0])+ "/" + String(y[1]);
var ctxx = document.getElementById('piechart');
new Chart(ctxx, {
  type: "pie",
  data: {
    labels: x,
    datasets: [{
      backgroundColor: pieColors,
      data: y
    }]
  },
  options: {
    title: {
      display: true,
      text: "Tổng số tín chỉ: " + mes
    }
  }
});