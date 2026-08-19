# Java Mobile

Java Mobile is a 5G mobile network simulator written in Java. It is designed to be a lightweight and flexible tool for simulating 5G networks, including gNBs (5G base stations), UEs (user equipment), and the radio layer. It also integrates with your real 5G core network via the [NGAP proxy](https://github.com/samuelh2005/ngap-proxy)

## Features TODO

### Milestone 1

- [ ] **gNB (5G Base Station)**
  - [ ] gNB state machines
  - [ ] gNB ZMQ connection to the [NGAP proxy](https://github.com/samuelh2005/ngap-proxy)
- [ ] **Radio layer** (not RF, simplistic but believable)
  - [ ] Lightweight Pathloss / RSRP model
  - [ ] Small Gaussian jitter
  - [ ] tx power control
- [ ] **Abstract modem implementation**
  - [ ] UE state machines
  - [ ] UE connection to the gNB
  - [ ] NAS message handling
  - [ ] High level UE API
  - [ ] Sim card mounting and unmounting
  - [ ] SMS over NAS
- [ ] Sim Cards (Holds subscriber keys)

### Milestone 2

- [ ] **gNB (5G Base Station)**
  - [ ] GTP-U/UDP connection to UPF
  - [ ] Handle UE IP packets from the radio link
- [ ] **Abstract modem implementation**
  - [ ] NAS PDU handling
  - [ ] IP packet handling
  - [ ] IMS connection logic
  - [ ] SMS over IMS
  - [ ] Voice over IMS
  - [ ] Extend API for data packets

## License

This project is licensed under the GNU Lesser General Public License v3.0 (LGPL-3.0) or later. See the [LICENSE](LICENSE) file for details.
