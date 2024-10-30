<template>
  <div>
    <el-page-header @back="goBack" content="求助人员定位展示"></el-page-header>
    <div id="container">
    </div>
  </div>
</template>

<script>

export default {
  name: "volunteer_findPosition",
  data() {
    return {
      preGet: true,
      longitude: 23.20211561621817,//经度
      latitude: 113.38094520264053//纬度
    }
  },
  mounted: function () {
    //调用已经被mounted的方法
    this.getPosition();
  },
  methods: {
    goBack() {
      this.$router.push("/home/volunteer/volunteer_task");
    },
    getPosition() {
      {
        //设置经纬度是传过来的经纬度信息
        this.longitude=this.$route.params.task_longitude
        this.latitude=this.$route.params.task_latitude
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "https://map.qq.com/api/gljs?v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&callback=getPosition";
        document.body.appendChild(script);
        //消除之前的填写按钮
        this.preGet = false;
        var center = new TMap.LatLng(23.20211561621817, 113.38094520264053);//设置中心点坐标
        //初始化地图
        var map = new TMap.Map("container", {
          zoom: 17, // 设置地图缩放
          center: center
        });

        //初始化marker图层
        var markerLayer = new TMap.MultiMarker({
          id: 'marker-layer',
          map: map,
          geometries: [
            //点标记数据数组
            {
              // 标记位置(经度，纬度，高度)
              // position: center,
              position: new TMap.LatLng(this.longitude, this.latitude, 50),
              id: 'marker',
            },
          ],
        });
        var longitude;
        var latitude;
        //监听点击事件添加marker
        map.on("click", (evt) => {
            markerLayer.add({
              position: evt.latLng,
            });
            this.longitude = evt.latLng.getLat();
            this.latitude = evt.latLng.getLng();

            //进行弹框弹出进行确认
            var str = "经度:" + this.longitude + "," + "纬度:" + this.latitude + ';'
            this.$confirm(str, '确定您的位置信息？', {
              confirmButtonText: '确定',
              cancelButtonText: '重选',
              ///截切的位置
              // callback: action => {
              // }
            })
              //确认的调用
              .then(() => {
                this.$message({
                  type: 'success',
                  message: '成功选取位置'
                  // message: `action: ${action}`
                });
                //确认位置信息后
                console.log("test");
                localStorage.setItem("longitude", this.longitude);
                localStorage.setItem("latitude", this.latitude);
                this.$router.push({path: '/home/help/call_help_b'});
              })
              //取消的调用
              .catch(() => {
                //腾讯地图：删除点标记
                markerLayer.setGeometries([])
                // this.$message({
                //   type: 'info',
                //   message: '已取消跳转',
                // });
              });

          }
        )
      }
    },
  }
}
</script>

<style scoped>

#container {
  high: 100%;
}

</style>
