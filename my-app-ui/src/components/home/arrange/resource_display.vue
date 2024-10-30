<template>
  <!-- ECharts图表测试 -->
  <div id="charts" style="width:100%;height:100%">
    <div id="main" :style="{width:'100%',height:'100%'}"></div>
  </div>
</template>

<script>
import {
  getOrgResourceNum,
  getTotalDemandNum
} from "../../../api/arrange";

export default {
  name: "resource_display",
  data() {
    return {
      totalDemand: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
      orgResource: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
      maxNum: 250,
      minNum: 0,
      betweenSpace: 50,
      //用来控制页面的更新，每次只更新一次
    }
  },
  mounted() {
    //貌似下面的加载eCharts的方法代码直接写在这里是不行的,因为生命周期未必在这里装载完dom对象
    this.updatePicture()
  },
  methods: {
    updatePicture() {
      // alert("Hi")
      getTotalDemandNum().then(response => {
        console.log("methodA方法")
        console.log("&&&&&&&&&&&")
        console.log(response.data)
        // this.totalDemand=[1,2, 3, 6, 0, 0, 1, 1, 0, 1, 3, 2]
        this.totalDemand = response.data
        for (var i = 0; i < response.data.length; i++) {
          this.maxNum = this.maxNum > response.data[i] ? this.maxNum : response.data[i];
        }

        ///////////////////////////////getOrgResourceNum要嵌套进这里面,否则可能就是异步请求，会出现数据不加载的错误/////////////////////
        getOrgResourceNum({orgId: localStorage.getItem("l_orgId")}).then(response => {
          //this.test放在外面是不行的，放在外面就会加载得比里面更快
          this.orgResource = response.data
          for (var i = 0; i < response.data.length; i++) {
            this.maxNum = this.maxNum > response.data[i] ? this.maxNum : response.data[i];
          }
          if (this.maxNum < 100) {
            this.maxNum = 100;
          } else if (this.maxNum < 500) {
            this.maxNum = 500;
          } else if (this.maxNum < 1000) {
            this.maxNum = 1000;
          } else if (this.maxNum < 1500) {
            this.maxNum = 1500;
          } else if (this.maxNum < 2000) {
            this.maxNum = 2000;
          }
          this.betweenSpace = this.maxNum / 5;
          console.log("methodB方法")
          this.generatePic()
        })
      })
    },
    generatePic() {
      //加载图
      let myChart = this.$echarts.init(document.getElementById('main'))
      let option;
      option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            crossStyle: {
              color: '#999'
            }
          }
        },
        toolbox: {
          feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        legend: {
          // data: ['求助物资总需求', '本组织总供应', 'Temperature']
          data: ['“待救援”需求', '本组织总供应']
        },
        xAxis: [
          {
            type: 'category',
            data: ["口罩", "绷带", "担架", "降压药", "抗过敏药", "饮用水", "充饥食品", "帐篷", "车位", "船位"],
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '份数',
            min: this.minNum,
            max: this.maxNum,
            interval: this.betweenSpace,
            axisLabel: {
              formatter: '{value} /人'
            }
          }
        ],
        series: [
          {
            name: '“待救援”需求',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value + ' ml';
              }
            },
            data: this.totalDemand
          },
          {
            name: '本组织总供应',
            type: 'bar',
            tooltip: {
              valueFormatter: function (value) {
                return value + ' ml';
              }
            },
            data: this.orgResource
          }
        ]
      };

      option && myChart.setOption(option);
    }
  }

}
</script>
