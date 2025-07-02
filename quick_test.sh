#!/bin/bash

# 快速测试脚本 - 验证申请职位系统基本功能

BASE_URL="http://localhost:8080"

echo "=== 申请职位系统快速测试 ==="
echo "Base URL: $BASE_URL"
echo ""

# 检查服务是否启动
echo "1. 检查服务状态..."
if curl -s "$BASE_URL" > /dev/null; then
    echo "✅ 服务已启动"
else
    echo "❌ 服务未启动，请先启动项目"
    exit 1
fi

echo ""

# 测试用户注册
echo "2. 测试用户注册..."
USER_RESPONSE=$(curl -s -X POST "$BASE_URL/api/users/register" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "测试用户",
    "email": "test@example.com",
    "phoneNumber": "13900139000"
  }')

echo "用户注册响应: $USER_RESPONSE"

# 提取用户ID
USER_ID=$(echo $USER_RESPONSE | grep -o '"id":[0-9]*' | cut -d':' -f2)
if [ -n "$USER_ID" ]; then
    echo "✅ 用户注册成功，ID: $USER_ID"
else
    echo "❌ 用户注册失败"
fi

echo ""

# 测试职位列表
echo "3. 测试职位列表..."
JOB_RESPONSE=$(curl -s "$BASE_URL/api/jobs?page=1&size=5")
echo "职位列表响应: $JOB_RESPONSE"

# 提取职位ID
JOB_ID=$(echo $JOB_RESPONSE | grep -o '"id":[0-9]*' | head -1 | cut -d':' -f2)
if [ -n "$JOB_ID" ]; then
    echo "✅ 获取职位列表成功，第一个职位ID: $JOB_ID"
else
    echo "❌ 获取职位列表失败"
fi

echo ""

# 测试职位详情
if [ -n "$JOB_ID" ]; then
    echo "4. 测试职位详情..."
    JOB_DETAIL_RESPONSE=$(curl -s "$BASE_URL/api/jobs/$JOB_ID")
    echo "职位详情响应: $JOB_DETAIL_RESPONSE"
    
    if echo $JOB_DETAIL_RESPONSE | grep -q "success"; then
        echo "✅ 获取职位详情成功"
    else
        echo "❌ 获取职位详情失败"
    fi
fi

echo ""

# 测试申请提交（如果有用户和职位数据）
if [ -n "$USER_ID" ] && [ -n "$JOB_ID" ]; then
    echo "5. 测试申请提交..."
    APPLICATION_RESPONSE=$(curl -s -X POST "$BASE_URL/api/applications" \
      -H "Content-Type: application/json" \
      -d "{
        \"jobId\": $JOB_ID,
        \"applicantId\": $USER_ID,
        \"resumeId\": 1,
        \"coverLetter\": \"这是一个测试申请\"
      }")
    
    echo "申请提交响应: $APPLICATION_RESPONSE"
    
    # 提取申请ID
    APPLICATION_ID=$(echo $APPLICATION_RESPONSE | grep -o '"application_id":[0-9]*' | cut -d':' -f2)
    if [ -n "$APPLICATION_ID" ]; then
        echo "✅ 申请提交成功，申请ID: $APPLICATION_ID"
        
        # 测试申请详情
        echo ""
        echo "6. 测试申请详情..."
        APPLICATION_DETAIL_RESPONSE=$(curl -s "$BASE_URL/api/applications/$APPLICATION_ID")
        echo "申请详情响应: $APPLICATION_DETAIL_RESPONSE"
        
        if echo $APPLICATION_DETAIL_RESPONSE | grep -q "success"; then
            echo "✅ 获取申请详情成功"
        else
            echo "❌ 获取申请详情失败"
        fi
    else
        echo "❌ 申请提交失败"
    fi
else
    echo "⚠️  跳过申请测试（缺少用户或职位数据）"
fi

echo ""
echo "=== 测试完成 ==="
echo ""
echo "如果所有测试都通过，您可以在Apifox中导入 apifox_collection.json 进行完整测试"
echo "详细测试指南请参考 APIFOX_TEST_GUIDE.md" 