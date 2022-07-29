









1. 从数据库中获取数据（以下代码以从sd卡中读取mp3数据为例）

public final Cursor query (Uri uri,                 //Url,即查询路径
        String[] projection,   //查询时希望获得的列，如果填null，则返回所有列
        String selection,      //查询时的条件，select语句中where用到，可填null
        String[] selectionArgs, //查询条件属性值
        String sortOrder,       //查询到的数据的默认排序，null则不进行排序
)












