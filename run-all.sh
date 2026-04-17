#!/usr/bin/env bash

set -euo pipefail

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"

if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven 未安装，请先安装 Maven 3.9+ 后再运行。"
  exit 1
fi

echo "在后台启动 Spring Cloud 示例服务..."

mkdir -p "${BASE_DIR}/logs"

start_service() {
  local module="$1"
  local log_file="${BASE_DIR}/logs/${module}.log"
  echo "启动 ${module}，日志: ${log_file}"
  (cd "${BASE_DIR}" && mvn -pl "${module}" spring-boot:run >"${log_file}" 2>&1 &) 
}

start_service "service-registry"
sleep 3
start_service "config-server"
sleep 3
start_service "user-service"
start_service "order-service"
start_service "api-gateway"

echo "全部启动命令已下发。"
echo "可通过以下地址验证："
echo "  Eureka:      http://localhost:8761"
echo "  Config:      http://localhost:8888/user-service/default"
echo "  Gateway API: http://localhost:8080/orders/1"
