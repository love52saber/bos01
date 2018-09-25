package cn.itcast.dao;

import cn.itcast.pojo.DecidedZone;

public interface DecidedZoneMapper {
    int deleteByPrimaryKey(String id);

    int insert(DecidedZone record);

    int insertSelective(DecidedZone record);

    DecidedZone selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DecidedZone record);

    int updateByPrimaryKey(DecidedZone record);
}