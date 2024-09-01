var xValues = [];
var yValues = [];
for (let i = 1; i < json.length+1; i++) {
	xValues.push("Học kỳ " + i);
	yValues.push(json[i-1]);
}

var barColors = 'rgb(30, 144, 255)';
var ctx = document.getElementById('barchart');
new Chart(ctx, {
  type: "bar",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      maxBarThickness: 40,
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "Điểm theo học kì"
    },
    scales: {
        yAxes: [{
            ticks: {
                beginAtZero: true, // Bắt đầu từ 0
                min: 0, // Giá trị tối thiểu
                max: 10 // Giá trị tối đa
            }
        }]
        }
  }
});