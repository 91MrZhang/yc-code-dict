# yc-code-dict-clickhouse

clickhouse简单接入

## CK运维控制

#### 服务启停
```shell script
systemctl stop clickhouse-server.service
systemctl start clickhouse-server.service
```
#### 进入命令行
```shell script
cd /usr/bin
clickhouse-client --port 9002 --user default --password 123456  -m
```

#### 清空表数据
```sql
ALTER TABLE audit_webapi.dwd_rec_entry_pass DELETE WHERE entime > toDateTime('2021-12-11 00:00:00') and entime < toDateTime('2021-12-13 23:59:59'); 

SELECT database, table, mutation_id, command, create_time, is_done FROM system.mutations where is_done<>1;

KILL MUTATION WHERE mutation_id='mutation_id';
```
#### 查询正在执行SQL

```sql
SELECT query_id, elapsed,query FROM system.processes where elapsed > 100  ORDER BY elapsed desc;

KILL QUERY WHERE query_id = 'ff695827-dbf5-45ad-9858-a853946ea140' ASYNC;
```

#### 查询SQL是否命中索引
```shell script
clickhouse-client -h localhost --port 9002 --password 123456 --send_logs_level=trace <<< "
select *  from audit_webapi.dwd_rec_gantry_trade 
where transtime>='2020-05-06 00:00:00' 
and transtime<='2022-01-27 23:59:59' 
order by transtime asc limit 50
" > /dev/null
```

#### SQL性能测试
```shell script
echo "select *  from audit_webapi.dwd_rec_gantry_trade where transtime>='2020-05-06 00:00:00' and transtime<='2022-01-27 23:59:59' and vehicleplate like '%_1' and gantrytype = '3'  and vehicletype=1 and left(passid,2) = '01'  and left(passid,2) = '02'  order by transtime asc limit 50"| clickhouse-benchmark -i 10 --port 9002 --password 123456
```

#### 建表demo
```sql
CREATE TABLE audit_wbapi.dwd_kw_image
(
    `id` String,
    `relatedstatus` Int32,
    `stationid` Nullable(String),
    `stationmjh` Nullable(String),
    `tolllandhex` Nullable(String),
    `tolllanegch` Nullable(String),
    `tollanegch` Nullable(String),
    `laneid` Nullable(String),
    `carsn` Nullable(String),
    `carsncolor` Nullable(String),
    `lanetype` Nullable(String),
    `vehspeed` Nullable(Int32),
    `vehfeaturecode` Nullable(String),
    `facefeaturecode` Nullable(String),
    `optime` DateTime,
    `direction` Nullable(Int32),
    `tollid` Nullable(String),
    `vehiclesignid` Nullable(String),
    `gantryordernum` Nullable(Int32),
    `carsntail` Nullable(String),
    `carsncolortail` Nullable(String),
    `cameranum` Nullable(Int32),
    `hourbatchno` Nullable(String),
    `shootposition` Nullable(Int32),
    `identifytype` Nullable(String),
    `vehiclemodel` Nullable(String),
    `vehiclecolor` Nullable(String),
    `verifycode` Nullable(String),
    `rectime` Nullable(String),
    `dataprocmark` Nullable(String),
    `dataproctime` Nullable(String),
    `camrecotype` Nullable(String),
    `gantryhex` Nullable(String),
    `matchstatus` Nullable(Int32),
    `validstatus` Nullable(Int32),
    `dealstatus` Nullable(Int32),
    `relatetradeid` Nullable(String),
    `allrelatetradeid` Nullable(String),
    `stationdbtime` Nullable(String),
    `stationdealtime` Nullable(String),
    `stationvalidtime` Nullable(String),
    `stationmatchtime` Nullable(String),
    `tradeid` Nullable(String),
    `type` Nullable(Int32)
)
ENGINE = MergeTree
PARTITION BY toYYYYMMDD(optime)
ORDER BY (id,
 optime,
 relatedstatus)
SETTINGS index_granularity = 8192;
```