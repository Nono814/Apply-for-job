#!/bin/bash

# 申请职位接口测试脚本

BASE_URL="http://localhost:8080"

echo "=== 申请职位接口测试 ==="

# 1. 测试提交职位申请
echo "1. 测试提交职位申请..."
curl -X POST "$BASE_URL/api/applications" \
  -H "Content-Type: application/json" \
  -d '{
    "jobId": 1,
    "applicantId": 1,
    "resumeId": 1,
    "coverLetter": "我对这个职位很感兴趣，希望有机会加入贵公司。"
  }' | jq '.'

echo -e "\n"

# 2. 测试获取申请状态
echo "2. 测试获取申请状态..."
curl -X GET "$BASE_URL/api/applications/1" | jq '.'

echo -e "\n"

# 3. 测试根据申请人ID获取申请列表
echo "3. 测试根据申请人ID获取申请列表..."
curl -X GET "$BASE_URL/api/applications/applicant/1" | jq '.'

echo -e "\n"

# 4. 测试根据职位ID获取申请列表
echo "4. 测试根据职位ID获取申请列表..."
curl -X GET "$BASE_URL/api/applications/job/1" | jq '.'

echo -e "\n"

# 5. 测试根据状态获取申请列表
echo "5. 测试根据状态获取申请列表..."
curl -X GET "$BASE_URL/api/applications/status/pending" | jq '.'

echo -e "\n"

# 6. 测试分页获取申请列表
echo "6. 测试分页获取申请列表..."
curl -X GET "$BASE_URL/api/applications?page=1&size=10" | jq '.'

echo -e "\n"

# 7. 测试更新申请状态
echo "7. 测试更新申请状态..."
curl -X PUT "$BASE_URL/api/applications/1/status?status=reviewing" | jq '.'

echo -e "\n"

echo "=== 测试完成 ===" 