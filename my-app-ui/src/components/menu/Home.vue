<template>
  <!-- ECharts图表测试 -->
  <div id="charts" style="width:100%;height:100%">
    <div id="main" :style="{width:'100%',height:'100%'}"></div>
  </div>
</template>

<script>

import {getMembersNum} from "../../api/arrange";

export default {
  name: "Home",
  data() {
    return {
      resultForm: {
        citizenNum: 120,
        volunteerNum: 60,
        leaderNum: 20,
        JiuYuanZhong: 50,
        YiFenPei: 30,
        YiWanCheng: 20,
        DaiJiuYuan: 90
      }
    }
  },
  mounted() {
    getMembersNum().then(response => {
      if (response.data != null) {
        this.resultForm = response.data
      }
      this.test()
    })
  },
  methods: {
    test() {
      let myChart = this.$echarts.init(document.getElementById('main'))
      var option;

      option = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          data: [
            '市民',
            '志愿者',
            '领导',
            '待救援',
            '已分配',
            '救援中',
            '已完成',
          ]
        },
        series: [
          {
            name: '人员构成图',
            type: 'pie',
            selectedMode: 'single',
            radius: [0, '30%'],
            label: {
              position: 'inner',
              fontSize: 14
            },
            labelLine: {
              show: false
            },
            data: [
              {value: this.resultForm.citizenNum, name: '市民'},
              {value: this.resultForm.volunteerNum, name: '志愿者'},
              {value: this.resultForm.leaderNum, name: '领导'}
              // {value: this.leaderNum, name: '领导', selected: true}
            ]
          },
          {
            name: '求助概况图',
            type: 'pie',
            radius: ['45%', '60%'],
            labelLine: {
              length: 30
            },
            label: {
              formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
              backgroundColor: '#F6F8FC',
              borderColor: '#8C8D8E',
              borderWidth: 1,
              borderRadius: 4,
              rich: {
                a: {
                  color: '#6E7079',
                  lineHeight: 22,
                  align: 'center'
                },
                hr: {
                  borderColor: '#8C8D8E',
                  width: '100%',
                  borderWidth: 1,
                  height: 0
                },
                b: {
                  color: '#4C5058',
                  fontSize: 14,
                  fontWeight: 'bold',
                  lineHeight: 33
                },
                per: {
                  color: '#fff',
                  backgroundColor: '#4C5058',
                  padding: [3, 4],
                  borderRadius: 4
                }
              }
            },
            data: [
              {value: this.resultForm.DaiJiuYuan, name: '待救援'},
              {value: this.resultForm.YiFenPei, name: '已分配'},
              {value: this.resultForm.JiuYuanZhong, name: '救援中'},
              {value: this.resultForm.YiWanCheng, name: '已完成'},
            ]
          }
        ]
      };

      option && myChart.setOption(option);

    }
  }
}
</script>
