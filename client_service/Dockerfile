FROM alpine:3.21.3

# Install necessary packages, e.g., if your service requires libc compatibility
RUN apk add --no-cache libc6-compat && mkdir -p /main_project

COPY ./target/client_service /main_project/

WORKDIR /main_project

ENTRYPOINT ["./client_service"]
